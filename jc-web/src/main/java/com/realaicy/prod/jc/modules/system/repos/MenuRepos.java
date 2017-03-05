package com.realaicy.prod.jc.modules.system.repos;

import com.realaicy.prod.jc.lib.core.data.jpa.BaseJPARepository;
import com.realaicy.prod.jc.modules.system.model.Menu;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by realaicy on 2016/3/13.
 * XXX
 */
@SuppressWarnings("JpaQlInspection")
public interface MenuRepos extends BaseJPARepository<Menu, BigInteger> {


    /**
     * Find menus by user.
     *
     * @return the list
     */
    @Query(value = "SELECT menu FROM Menu menu"
            + " WHERE menu.parent.id=1 ORDER By menu.resWeight ")
    List<Menu> findAllMenus();

}
