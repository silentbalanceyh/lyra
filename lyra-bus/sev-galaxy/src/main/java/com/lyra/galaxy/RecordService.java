package com.lyra.galaxy;

import com.lyra.meta.Record;

/**
 * @author Lang
 * @package com.lyra.galaxy
 * @name RecordService
 * @class com.lyra.galaxy.RecordService
 * @date 2014年11月27日 上午3:17:16
 * @see
 */
public interface RecordService {
	/**
	 * For Policy: ( AUTO, GUID, RANDOM, MULTI )
	 *
	 * @param record
	 * @return
	 */
	Record create(final Record record);

	/**
	 * For Policy: ( AUTO, GUID, RANDOM, MULTI )
	 *
	 * @param record
	 * @return
	 */
	Record modify(final Record record);

	/**
	 * @param record
	 * @return
	 */
	Record merge(final Record record);

	/**
	 * @param idJson
	 * @return
	 */
	boolean removeById(final String idJson);

	/**
	 * @param idJson
	 * @return
	 */
	Record findById(final String idJson);
}
