package com.tugcankoparan.example;

import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.*;

public class EmployeeSpecs {
    public static Specification<Employee> getEmployeesByNameSpec(String name) {
        return new Specification<Employee>() {
			private static final long serialVersionUID = 1L;
			@Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query,  CriteriaBuilder criteriaBuilder) {
                Predicate equalPredicate = criteriaBuilder.equal(root.get(Employee_.name), name);
                return equalPredicate;
            }
        };
    }

    public static Specification<Employee> getEmployeesByPhoneTypeSpec(PhoneType phoneType) {
        return new Specification<Employee>() {
			private static final long serialVersionUID = 1L;
			@Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                ListJoin<Employee, Phone> phoneJoin = root.join(Employee_.phones);
                Predicate equalPredicate = criteriaBuilder.equal(phoneJoin.get(Phone_.type), phoneType);
                return equalPredicate;
            }
        };
    }
}