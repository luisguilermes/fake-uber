package fake.uber.merchant.profile.dataprovider.postgres.statements

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

    const val GET_NEARBY_BUSINESS = """
        SELECT 
            id, 
            name,
            lng
            lat
        FROM businesses
        ORDER BY created_at ASC
        LIMIT :size OFFSET :page
    """
}
