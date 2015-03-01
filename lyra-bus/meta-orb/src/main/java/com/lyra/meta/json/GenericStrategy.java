package com.lyra.meta.json;

import static com.lyra.util.instance.ArrayInstance.arrayList;
import static com.lyra.util.instance.MapInstance.hashMap;
import static com.lyra.util.instance.NullInstance.nullObj;
import static com.lyra.util.internal.Validator.nullable;
import static com.lyra.util.logger.Console.console;
import static com.lyra.util.logger.Logger.info;
import static com.lyra.util.logger.Logger.trace;
import static com.lyra.util.reflector.Factory.instance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyra.exception.AbstractSchemaException;
import com.lyra.exp.sys.ResourceIOException;
import com.lyra.meta.Record;
import com.lyra.meta.Strategy;
import com.lyra.meta.Value;
import com.lyra.mod.def.FieldSchema;
import com.lyra.res.Resources;
import com.lyra.res.Symbol;

/**
 * Generated implementation of strategy.
 *
 * @author Lang
 */
final class GenericStrategy implements Strategy {
	// ~ Instance Fields =====================================
	// region interal objects
	/**
	 * Generated data schema information *
	 */
	private transient final GenericSchema schema;
	/**
	 * Jackson object mapper tool *
	 */
	private transient final ObjectMapper mapper = new ObjectMapper();

	// ~ Constructors ========================================

	/**
	 * Default constructor and created by name;
	 *
	 * @param name
	 */
	GenericStrategy(final String name) {
		this.schema = instance(GenericSchema.class, name); // GenericSchema.instance(name);
	}

	// endregion

	// ~ Override Methods ====================================
	// region Create new records implementation

	/**
     *
     */
	@Override
	public List<Record> readRecords() {
		final URL dataUrl = getClass().getResource(
				this.schema.getConfigurator().getDataFilePath());
		final List<Record> rRecords = arrayList();
		if (nullable(dataUrl)) {
			throw new ResourceIOException(getClass(), "readLines");
		} else {
			info(getClass(), dataUrl.getFile());
			final List<String> dataLines = arrayList();
			String firstLine = Symbol.STR_NULL;
			try (final BufferedReader reader = new BufferedReader(
					new InputStreamReader(getClass().getResourceAsStream(
							this.schema.getConfigurator().getDataFilePath()),
							Resources.SYS_CN_ENCODING))) {
				String line = new String(reader.readLine().getBytes(
						Resources.SYS_CN_ENCODING), Resources.SYS_ENCODING);
				firstLine = line;
				while (!nullable(line)) {
					line = reader.readLine();
					if (!nullable(line) && line.length() > 0) {
						dataLines.add(line);
					}
				}
				rRecords.addAll(this.readLines(firstLine, dataLines));
			} catch (IOException ex) {
				trace(getClass(), ex);
			}
		}
		console(getClass(), rRecords);
		return rRecords;
	}

	/**
	 * Create default record
	 */
	@Override
	public Record createRecord() {
		final Record record = instance(GenericRecord.class,
				this.schema.getFullName());
		for (final String name : this.schema.getFields().keySet()) {
			record.set(name, this.schema.extractValue(name));
		}
		return record;
	}

	/**
	 * Created record via field map / column map
	 */
	@Override
	public Record createRecord(final Map<String, Value<?>> inputMap) {
		final Record record = instance(GenericRecord.class,
				this.schema.getFullName());
		for (final String name : this.schema.getFields().keySet()) {
			if (inputMap.containsKey(name)) {
				// Field
				record.set(name, inputMap.get(name));
			} else {
				// Column
				record.set(name, inputMap.get(this.getCtoF().get(name)));
			}
		}
		return record;
	}

	/**
	 * Create record via json string.
	 */
	@Override
	public Record createRecord(final String jsonContent) {
		final Record record = instance(GenericRecord.class,
				this.schema.getFullName());
		Map<String, String> inputMap = hashMap(true);
		try {
			inputMap = this.mapper.readValue(jsonContent,
					new TypeReference<Map<String, String>>() {
					});
		} catch (IOException e) {
			trace(getClass(), e);
		}
		for (final String name : this.schema.getFields().keySet()) {
			record.set(name, this.schema.extractValue(name, inputMap.get(name)));
		}
		return record;
	}

	// endregion

	/**
     *
     */
	@Override
	public Map<String, FieldSchema> getKeys() {
		return this.schema.getKeys();
	}

	/**
     *
     */
	@Override
	public Map<String, FieldSchema> getFields() {
		return this.schema.getFields();
	}

	/**
     *
     */
	@Override
	public String getTable() {
		return this.schema.getTable();
	}

	/**
	 * Get schema validation error information
	 */
	@Override
	public AbstractSchemaException getError() {
		return this.schema.getError();
	}

	/**
     *
     */
	@Override
	public Map<String, String> getCtoF() {
		return this.schema.getCtoF();
	}

	/**
     *
     */
	@Override
	public Map<String, String> getFtoC() {
		return this.schema.getFtoC();
	}

	/**
	 * Set schema validation error information
	 */
	@Override
	public void setError(final AbstractSchemaException error) {
		this.schema.setError(error);
	}

	/** */
	@Override
	public List<String> getOrderColumns() {
		return this.schema.getOrderColumns();
	}

	// region Private members

	// ~ Private Methods =====================================
	private List<Record> readLines(final String columnLine,
			final List<String> dataLines) {
		info(getClass(), "Columns in sequence from data file: " + columnLine);
		final List<Record> rData = arrayList();
		final String[] columns = columnLine.split(",");
		// Extract data from data lines
		for (final String dataLine : dataLines) {
			final String[] colData = dataLine.split(",");
			final Record record = this.createRecord();
			// info(getClass(),"Column length: " + columns.length +
			// ",Data length: " + colData.length);
			if (columns.length == colData.length) {
				// Extract data from data column
				for (int i = 0; i < columns.length; i++) {
					final String fieldName = this.schema.getCtoF().get(
							columns[i]);
					final Value<?> itemValue = this.schema.extractValue(
							fieldName, this.extractValue(colData[i]));
					record.set(fieldName, itemValue);
				}
			}
			rData.add(record);
		}
		return rData;
	}

	/**
	 * Set filter to set some place holder charactor. *
	 */
	private String extractValue(final String inputValue) {
		String retValue = nullObj();
		if (nullable(inputValue)) {
			retValue = "";
		} else if (inputValue.trim().equals("${NULL}")) {
			retValue = "";
		} else {
			retValue = inputValue;
		}
		return retValue;
	}
	// endregion

}
