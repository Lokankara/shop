package org.store.product.service;

import org.store.product.dao.RegistryDao;
import org.store.product.web.domain.Product;

public class RegistryService implements RegistryDao {
    private final RegistryDao registryDao;
    public RegistryService(RegistryDao registryDao) {
        this.registryDao = registryDao;
    }
    @Override
    public int save(Product product){
        return registryDao.save(product);};

    @Override
    public int update(Product product){
        return registryDao.update(product);};

    @Override
    public int delete(Product product){
        return registryDao.delete(product);};
}
