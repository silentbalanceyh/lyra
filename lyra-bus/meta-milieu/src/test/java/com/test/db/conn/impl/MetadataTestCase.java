package com.test.db.conn.impl;

import static com.lyra.util.Instance.singleton;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import jodd.util.StringPool;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyra.db.conn.MetadataConn;
import com.lyra.meta.database.Metadata;
import com.lyra.res.Constants;
import com.lyra.res.Resources;
import com.lyra.util.DatabaseDecovery;

/**
 * 
 *
 * @author Lang
 * @see
 */
public class MetadataTestCase {
	// ~ Static Fields =======================================
	/** **/
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MetadataTestCase.class);
	// ~ Instance Fields =====================================
	/** **/
	private transient final MetadataConn metaConn = singleton("com.lyra.db.conn.impl.MetadataConnImpl");

	// ~ Static Block ========================================
	// ~ Static Methods ======================================
	// ~ Constructors ========================================
	// ~ Abstract Methods ====================================
	// ~ Override Methods ====================================
	// ~ Methods =============================================
	/** **/
	@Test
	public void testMetadata() {
		final Metadata metadata = this.metaConn.getMetadata();
		assertNotNull("[T] Metadata is null.", metadata);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("[TD] Metadata = " + metadata);
		}
	}

	/** **/
	@Test
	public void testVersionFlag() {
		final Metadata metadata = this.metaConn.getMetadata();
		final String versionFlag = DatabaseDecovery.getSqlServer(metadata
				.getProductVersion());
		assertEquals("[T] Version flag is not match.", versionFlag,
				metadata.getVersionFlag());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("[TD] Version flag = " + metadata.getVersionFlag());
		}
	}

	/** **/
	@Test
	public void testSqlFile() {
		final Metadata metadata = this.metaConn.getMetadata();
		final String versionFlag = DatabaseDecovery.getSqlServer(metadata
				.getProductVersion());
		assertEquals("[T] Sql filename is not match.",
				metadata.getDatabaseCategory() + versionFlag,
				metadata.getSqlFile());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("[TD] Sql filename = " + metadata.getSqlFile());
		}
	}
	/**
	 * 
	 */
	@Test
	public void testSqlExec(){
		final Metadata metadata = this.metaConn.getMetadata();
		final String scriptFile = Resources.DB_SQL_DIR + metadata.getSqlFile() + StringPool.DOT + Constants.FEX_SQL;
		boolean ret = this.metaConn.loadSqlFile(Resources.class.getResourceAsStream(scriptFile));
		assertTrue("[T] Executed failure.",ret);
	}
	// ~ Private Methods =====================================
	// ~ hashCode,equals,toString ============================
}
