package fake.uber.business.dataprovider.postgres.statements

object BusinessStatements {
    const val GET_BUSINESS_BY_ID = """
            SELECT 
                id, 
                name,
                lng
                lat
            FROM businesses
            WHERE id = :id
        """

    const val INSERT_BUSINESS = """
        INSERT INTO businesses (id, name, lng, lat) 
        VALUES (:id, :name, :lng, :lat)
    """
}
