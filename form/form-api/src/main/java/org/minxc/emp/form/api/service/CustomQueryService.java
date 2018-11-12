package org.minxc.emp.form.api.service;

import java.util.List;

public interface CustomQueryService {

    public List<?> getAllDataByAlias(String alias);

    public List<?> getAll();
}
