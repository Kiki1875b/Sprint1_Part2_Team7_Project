package team7.hrbank.domain.Department;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    //이름으로 부서 존재여부 확인
    boolean existsByName(String name); // 부서 이름 중복 여부 확인

    //검색어 유무에 따라 전체 or 필터링된 부서목록 반환
    @Query("SELECT d FROM Department d " +
            "WHERE (:nameOrDescription IS NULL OR " + //검색어가 입력되지 않았거나
            "LOWER(d.name) LIKE LOWER(CONCAT('%', :nameOrDescription, '%')) OR " +
            "LOWER(d.description) LIKE LOWER(CONCAT('%', :nameOrDescription, '%')))")
    Page<Department> findByCriteria(@Param("nameOrDescription") String nameOrDescription,
                                    Pageable pageable);

    //이전페이지 마지막 Id 이후 Id를 가진 요소 중 검색어 유무에 따라 전체 or 필터링된 부서목록 반환
    @Query("SELECT d FROM Department d " +
            "WHERE d.id > :lastId AND " +
            "(:nameOrDescription IS NULL OR " + //검색어가 입력되지 않았거나
            "LOWER(d.name) LIKE LOWER(CONCAT('%', :nameOrDescription, '%')) OR " +
            "LOWER(d.description) LIKE LOWER(CONCAT('%', :nameOrDescription, '%')))")
    Page<Department> findByCriteriaWithLastId(@Param("nameOrDescription") String nameOrDescription,
                                              @Param("lastId") Integer lastId,
                                              Pageable pageable);

    //부서 아이디로 단건 조회
    Department findById(long id);

}
