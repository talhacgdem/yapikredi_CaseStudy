package com.talhacgdem.yapikredi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.talhacgdem.yapikredi.model.Leave;
import java.util.Date;
import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
    List<Leave> findLeavesByStatus(String status);
    List<Leave> findLeavesByEmployeeId(Long employeeId);
    List<Leave> findByStartDateAfterAndEmployeeId(Date date, Long id);
}
