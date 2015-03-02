package com.lyra.meta.sys;

import com.lyra.db.conn.MetadataConn;
import com.lyra.exp.sys.ResourceIOException;
import com.lyra.res.Resources;

import org.springframework.dao.DataAccessException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import static com.lyra.util.logger.Logger.info;
import static com.lyra.util.logger.Logger.trace;
import static com.lyra.util.reflector.Factory.singleton;
import static com.lyra.util.instance.BufferInstance.builder;
import static com.lyra.util.internal.Validator.nullable;

/**
 * @author Lang
 * @package com.test.lyra.meta.sys
 * @name SystemDataDeployer
 * @class com.test.lyra.meta.sys.SystemDataDeployer
 * @date Oct 14, 2014 11:17:08 PM
 * @see
 */
public final class SystemDataDeployer {

	// ~ Instance Fields =====================================
	/**
	 * Database environment, this context is only used in SQL database *
	 */
	private transient final MetadataConn dbCtx;

	// ~ Constructors ========================================

	/**
	 * Private constructor *
	 */
	SystemDataDeployer() {
		this.dbCtx = singleton(Resources.getConnectionPool());
		System.out.println(this.dbCtx == null);
	}

	// ~ Override Methods ====================================

	/**
	 * Execute system sql file to initialize the database *
	 */
	public boolean deploySystemData() {
		final URL sqlUrl = Resources.class.getResource(Resources.DB_SQL_FILE);
		boolean ret = false;
		if (nullable(sqlUrl)) {
			throw new ResourceIOException(getClass(), "deploySystemData");
		} else {
			// Read sql file content
			final StringBuilder sqlContent = builder();
			info(getClass(), sqlUrl.getFile());
			try (final BufferedReader reader = new BufferedReader(
					new InputStreamReader(Resources.class
							.getResourceAsStream(Resources.DB_SQL_FILE),
							Resources.SYS_ENCODING))) {
				String line = reader.readLine();
				while (!nullable(line)) {
					// Ignore line comments;
					if (!line.trim().startsWith("--")) {
						sqlContent.append(line);
					}
					line = reader.readLine();
				}
			} catch (IOException ex) {
				trace(getClass(), ex);
			}
			// Communicate with database and execute sql statements
			try {
				this.dbCtx.execute(sqlContent.toString());
				ret = true;
			} catch (DataAccessException ex) {
				trace(getClass(), ex);
			}
		}
		return ret;
	}
}
