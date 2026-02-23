package repository.custom.impl;

import model.dto.RentNReturn;
import repository.custom.RentNReturnRepository;

import java.sql.SQLException;
import java.util.List;

public class RentNReturnRepositoryImpl implements RentNReturnRepository {

    @Override
    public boolean create(RentNReturn rentNReturn) throws SQLException {
        return false;
    }

    @Override
    public boolean update(RentNReturn rentNReturn) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteById(String s) throws SQLException {
        return false;
    }

    @Override
    public RentNReturn getById(String s) throws SQLException {
        return null;
    }

    @Override
    public List<RentNReturn> getAll() throws SQLException {
        return List.of();
    }
}
