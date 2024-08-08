package com.winnguyen1905.technologystore.repository.specification;

import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Pair;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;

public class CustomSpecification<T> {

    public CustomSpecification() {}

    // public static <F> Class<F> constructInstance(Pair<Class<F>, String> joining) throws Exception {
    //     String entityName = StringUtils.convertSnakeToCamelCase(joining) + "Entity";
    //     Class<F> clazz = (Class<F>) Class.forName("com.winnguyen1905.technologystore.entity." + entityName);
    //     return clazz;
    // }

    // public static <T, F> Join<T, F> performJoin(Class<F> fClass, Root<T> root, Pair<Class<F>, String> joining) {
    //     Join<T, F> join = root.join(joining + "s");
    //     return join;
    // }

    public static <T, F> Join<T, F> oneToMany(Pair<Class<F>, String> joining, Root<T> root) {
        return null;
    }

    public static <T, F> Path<T> manyToOne(Pair<Class<F>, String> joining, Root<T> root) {
        return null;
    }

    public static <T, F> Join<T, ?> joinTableManager(Pair<Class<F>, String> joining, Root<T> root) {
        if(joining.getSecond().endsWith("_O")) {
            String finalName = joining.getSecond().substring(0, joining.getSecond().indexOf("_O")) + "s";
            Join<T, F> join = root.join(finalName);
            return join;
        } {
            String finalName = joining.getSecond().substring(0, joining.getSecond().indexOf("_M"));
            Join<T, F> join = root.join(finalName);
            return join;
        }
    }

    public static <T, F>  Specification<T> construct() {
        return (root, query, builder) -> builder.isNotNull(root.get("id"));
    }

    public static <T, F> Specification<T> isValueLike(String stringLike, String col, Pair<Class<F>, String> joining) {
        return (root, query, builder) -> builder.like(
            (joining != null ? joinTableManager(joining, root) : root)
            .get(col), "%" + stringLike + "%");
    }
    
    public static <T, F> Specification<T> isTrue(Boolean value, String col, Pair<Class<F>, String> joining) {
        return (root, query, builder) -> builder.isTrue(
            (joining != null ? joinTableManager(joining, root) : root)
            .get(col));
    }

    public static <T, F> Specification<T> isFalse(Boolean value, String col, Pair<Class<F>, String> joining) {
        return (root, query, builder) -> builder.isFalse(
            (joining != null ? joinTableManager(joining, root) : root)
            .get(col));
    }

    public static <T, F> Specification<T> isWithinValueRange(int min, int max, String col, Pair<Class<F>, String> joining) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(
            (joining != null ? joinTableManager(joining, root) : root)
            .get(col), min, max);
    }

    public static <T, F> Specification<T> isEqualValue(Object value, String col, Pair<Class<F>, String> joining) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
            (joining != null ? joinTableManager(joining, root) : root)
            .get(col), value);
    }
    
    public static <T, F> Specification<T> isGreaterThanOrEqual(double value, String col, Pair<Class<F>, String> joining) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(
            (joining != null ? joinTableManager(joining, root) : root)
            .get(col), value);
    }

    public static <T, F> Specification<T> isLessThanOrEqual(double value, String col, Pair<Class<F>, String> joining) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(
            (joining != null ? joinTableManager(joining, root) : root)
            .get(col), value);
    }

    public static <T, F> Specification<T> isInList(List<String> list, String col, Pair<Class<F>, String> joining) {
        return (root, query, criteriaBuilder) ->
            (joining != null ? joinTableManager(joining, root) : root)
            .get(col).in(list);
    }
    /*
        Second request parameter filter: Get PermissionEntitys that have doctors with a specific speciality.
        This will require us to first join the PermissionEntity and doctor tables (OneToMany), and then applying the filter.
        To do this One to Many join (one PermissionEntity has many doctors), we need to use the Join criteria to accomplish it
     */


    // public static Specification<PermissionEntity> hasDoctorInSpeciality(String speciality) {
    //     return (root, query, builder) -> {
    //         Join<Doctor,PermissionEntity> PermissionEntityDoctors = root.join("doctors");
    //         return builder.equal(PermissionEntityDoctors.get("speciality"), speciality);
    //     };
    // } 
    // Third request parameter filter: Get PermissionEntitys in one of the specified cities
}