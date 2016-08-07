package repositories.couchbase;

/**
 * Created by takunnithan on 08/08/2016.
 */
public interface Repository {

    /**
     * Interface that defines standard CRUD operations for entities.
     *
     * @author takunnithan
     */

        /**
         * Find an entity by the specified ID and return a reference to
         * an instance of the specified type.
         *
         * @param id Unique ID of the entity
         * @param type Type of the entity to return
         * @return Reference to an instance of the specified type that
         * 	corresponds to the ID
         */
        <T> T findById(String id, Class<T> type);

        /**
         * Persist a new instance of the specified type in the repository.
         *
         * @param entity Source entity to be persisted
         * @param type The type of the entity to be persisted
         * @return Reference to the entity that has been persisted
         */
        <T> T create(T entity, Class<T> type);

        /**
         * Update an existing instance of the specified type in the repository.
         *
         * @param entity Source entity to be persisted
         * @param type The type of the entity to be persisted
         * @return Reference to the entity that has been persisted
         */
        <T> T update(T entity, Class<T> type);

        /**
         * Insert or update an instance of the specified type in the repository.
         *
         * @param entity Source entity to be persisted
         * @param type The type of the entity to be persisted
         * @return Reference to the entity that has been persisted
         */
        <T> T upsert(T entity, Class<T> type);

        /**
         * Delete the specified entity from the repository.
         *
         * @param entity Source entity to be deleted
         */
        <T> void delete(T entity);

}
