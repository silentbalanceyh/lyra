package com.test.lyra.db;

import static com.lyra.util.instance.ArrayInstance.arrayList;
import static com.lyra.util.instance.MapInstance.hashMap;
import static com.lyra.util.instance.SetInstance.hashSet;
import static com.lyra.util.logger.Logger.debug;
import static com.lyra.util.reflector.Factory.instance;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.Map;

import org.junit.Test;

import com.lyra.db.RecordDao;
import com.lyra.db.impl.DaoFactory;
import com.lyra.db.util.RecordUtil;
import com.lyra.meta.Context;
import com.lyra.meta.Record;
import com.lyra.meta.Value;
import com.lyra.meta.type.BooleanType;
import com.lyra.meta.type.NumberType;
import com.lyra.meta.type.StringType;
import com.lyra.mod.def.FieldSchema;
import com.lyra.res.Constants;
import com.lyra.res.Resources;

/**
 * @author Lang
 * @package com.test.lyra.db.test.db
 * @name AbstractDbTestCase
 * @class com.test.lyra.db.test.db.AbstractDbTestCase
 * @date Dec 4, 2014 9:31:38 PM
 * @see
 */
public abstract class AbstractDbTestCase {
	/**
	 * Record accessor instance for every sub-class instance *
	 */
	private final transient RecordDao accessor;
	/**
	 * Context for record *
	 */
	private final transient Context context;
	/**
	 * Batch processing data records *
	 */
	private static final int T_BATCH_SIZE = 5;

	/**
	 * public constructor *
	 */
	public AbstractDbTestCase(final String modelName) {
		this.accessor = DaoFactory.getRecordDao(modelName);
		this.context = instance(Resources.getContextClass(), modelName);
	}

	/**
	 * Return context environment for model *
	 */
	protected Context getContext() {
		return this.context;
	}

	/**
	 * Return record database accessor. *
	 */
	protected RecordDao getAccessor() {
		return this.accessor;
	}

	/**
	 * Return context primary key name. *
	 */
	protected String getPKName() {
		return this.context.getPK().getName();
	}

	/**
	 * @param seedStr
	 * @return
	 */
	protected Value<?> genValue(final FieldSchema schema, final String seedStr) {
		Value<?> retValue = null;
		final Random random = new Random();
		switch (schema.getType()) {
		case "NumberType":
		case "LongType":
		case "ShortType":
		case "IntType": {
			retValue = new NumberType(random.nextInt());
		}
			break;
		case "BooleanType": {
			retValue = new BooleanType(random.nextBoolean());
		}
			break;
		default: {
			retValue = new StringType(seedStr + UUID.randomUUID().toString());
		}
			break;
		}
		return retValue;
	}

	/**
	 * Generate new record of model
	 *
	 * @return
	 */
	protected Record genRecord() {
		final Record record = this.getContext().createRecord();
		final Set<String> fields = record.fieldNames();
		final Map<String, FieldSchema> fieldMap = this.getContext().getFields();
		for (final String field : fields) {
			final FieldSchema schema = fieldMap.get(field);
			record.set(field, this.genValue(schema, "Value"));
		}
		return record;
	}

	/**
	 * Generate updated record
	 *
	 * @param record
	 * @return
	 */
	protected Record genRecord(final Record record) {
		final Set<String> fields = record.fieldNames();
		final Map<String, FieldSchema> fieldMap = this.getContext().getFields();
		final Set<String> pkFilters = hashSet();
		if (this.getContext().getPolicy().equals(Constants.KP_MULTI)) {
			for (final FieldSchema schema : this.getContext().getPKs()) {
				pkFilters.add(schema.getName());
			}
		} else {
			pkFilters.add(this.getContext().getPK().getName());
		}
		for (final String field : fields) {
			final FieldSchema schema = fieldMap.get(field);
			if (!pkFilters.contains(field)) {
				record.set(field, this.genValue(schema, "Value"));
			}
		}
		return record;
	}

	/**
	 * @return
	 */
	protected List<Record> genRecords(final int genCount) {
		final List<Record> records = arrayList();
		for (int i = 0; i < genCount; i++) {
			records.add(this.genRecord());
		}
		return records;
	}

	/**
	 * @param postMode
	 */
	private void postDelRecord(final Record afterR) {
		if (this.getContext().getPolicy().equals(Constants.KP_MULTI)) {
			// Post processing
			final Map<String, Value<?>> pkMap = this.getPkMap(afterR);
			this.getAccessor().deleteById(pkMap);
		} else {
			// Post processing
			this.getAccessor().deleteById(afterR.get(this.getPKName()));
		}
	}

	private Map<String, Value<?>> getPkMap(final Record afterR) {
		final Map<String, Value<?>> pkMap = hashMap(true);
		if (this.getContext().getPolicy().equals(Constants.KP_MULTI)) {
			for (final FieldSchema schema : this.getContext().getPKs()) {
				pkMap.put(schema.getName(), afterR.get(schema.getName()));
			}
		}
		return pkMap;
	}

	private Record queryRecord(final Record afterR) {
		Record queriedR = null;
		if (this.getContext().getPolicy().equals(Constants.KP_MULTI)) {
			// Post processing
			final Map<String, Value<?>> pkMap = this.getPkMap(afterR);
			queriedR = this.getAccessor().queryById(pkMap);
		} else {
			queriedR = this.getAccessor().queryById(
					afterR.get(this.getPKName()));
		}
		return queriedR;
	}

	/**
     *
     */
	@Test
	public void testMassAddDelete1() {
		final List<Record> records = this.genRecords(2);
		final boolean ret = this.getAccessor().add(records);
		// JUnit Assert
		assertTrue("[T] Mass inserted results must be 'true'.", ret);
		if (ret) {
			// Post processing
			this.getAccessor().deleteAll();
		}
	}

	/**
     *
     */
	// @Test
	public void testMassAddDelete2() {
		final List<Record> records = this.genRecords(T_BATCH_SIZE);
		final boolean ret = this.getAccessor().add(records);
		if (ret) {
			final long intCount = this.getAccessor().countAll();
			debug(getClass(), "[TEST] There are '" + intCount
					+ "' records inserted.");
			// JUnit Assert
			assertEquals("[T] Inserted records' count number should match.",
					T_BATCH_SIZE, intCount);
			// Post processing
			this.getAccessor().deleteAll();
		}
	}

	/**
	 * Mass Update
	 */
	// @Test
	public void testMassUpdateDelete() {
		final List<Record> beforeRList = this.genRecords(T_BATCH_SIZE);
		// Single add because the system will read inserted records
		final List<Record> afterRList = arrayList();
		for (final Record record : beforeRList) {
			afterRList.add(this.getAccessor().add(record));
		}
		// Update
		for (final Record record : afterRList) {
			this.genRecord(record);
		}
		// Mass Update
		final boolean ret = this.getAccessor().update(afterRList);
		// JUnit Assert
		assertTrue("[T] Mass updated results must be 'true'.", ret);
		this.getAccessor().deleteAll();
	}

	/**
     *
     */
	// @Test
	public void testAddDelete() {
		final Record beforeR = this.genRecord();
		final Record afterR = this.getAccessor().add(beforeR);
		// JUnit Assert
		assertTrue("[T] The record must be sync after inserted!",
				RecordUtil.isEqual(beforeR, afterR));
		debug(getClass(), "[TEST] ( testAddDelete ) Record inserted: " + afterR);
		this.postDelRecord(afterR);
	}

	/**
     *
     */
	// @Test
	public void testQuery() {
		final Record beforeR = this.genRecord();
		final Record afterR = this.getAccessor().add(beforeR);
		debug(getClass(), "[TEST] ( testQuery ) Record inserted: " + afterR);
		final Record queriedR = this.queryRecord(afterR);
		// JUnit Assert
		assertTrue("[T] The record queried must be equal to added record.",
				RecordUtil.isEqual(queriedR, beforeR));
		this.postDelRecord(afterR);
	}

	/**
     *
     */
	// @Test
	public void testExist() {
		final Record beforeR = this.genRecord();
		final Record afterR = this.getAccessor().add(beforeR);
		debug(getClass(), "[TEST] ( testExist ) Record inserted: " + afterR);
		final boolean exist = this.getAccessor().exist(afterR);
		// JUnit Assert
		assertTrue(
				"[T] -- Record does not exist in database, please check result. ",
				exist);
		this.postDelRecord(afterR);
	}

	/**
     *
     */
	// @Test
	public void testQueryAll() {
		final List<Record> records = this.genRecords(T_BATCH_SIZE);
		final boolean ret = this.getAccessor().add(records);
		if (ret) {
			final List<Record> afterRs = this.getAccessor().queryAll();
			// JUnit Assert
			assertEquals(
					"[T] -- Record count should be matched after querying.",
					T_BATCH_SIZE, afterRs.size());
		}
	}

	/**
     *
     */
	// @Test
	public void testUpdate() {
		final Record beforeR = this.genRecord();
		final Record afterR = this.getAccessor().add(beforeR);
		debug(getClass(), "[TEST] ( testUpdate ) Record inserted: " + afterR);
		// Updated Fields
		final Record beforeUR = this.genRecord(afterR);
		final Record retUR = this.getAccessor().update(beforeUR);
		final Record afterUR = this.queryRecord(retUR);
		// JUnit Assert
		assertTrue("[T] The record queried must be equal to added record.",
				RecordUtil.isEqual(afterUR, beforeUR));
		this.postDelRecord(afterR);
	}

	/**
     *
     */
	// @Test
	public void testQueryByPage() {
		// Test start
		final List<Record> records = this.genRecords(12);
		final boolean ret = this.getAccessor().add(records);
		if (ret) {
			// PageSize = 10
			final List<Record> page11 = this.getAccessor().queryByPage(1, 10);
			final List<Record> page12 = this.getAccessor().queryByPage(2, 10);
			debug(getClass(),
					"[TEST] ( testQueryByPage ) Case 1 - Page 1 records: "
							+ page11.size());
			debug(getClass(),
					"[TEST] ( testQueryByPage ) Case 1 - Page 2 records: "
							+ page12.size());
			// PageSize = 20
			final List<Record> page21 = this.getAccessor().queryByPage(1, 20);
			debug(getClass(),
					"[TEST] ( testQueryByPage ) Case 2 - Page 1 records: "
							+ page21.size());
			// PageSize = 12
			final List<Record> page31 = this.getAccessor().queryByPage(1, 12);
			debug(getClass(),
					"[TEST] ( testQueryByPage ) Case 3 - Page 1 records: "
							+ page31.size());
			// Junit Assert for special page.
			assertEquals("[T] The page records do not match.", 2, page12.size());
			this.getAccessor().deleteAll();
		}
	}
}
