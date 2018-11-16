package com.esc.calllog.dao;

import com.esc.calllog.entity.CallLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallLogRepository extends JpaRepository<CallLogEntity, Long> {

}
