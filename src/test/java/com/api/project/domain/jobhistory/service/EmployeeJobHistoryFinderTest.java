package com.api.project.domain.jobhistory.service;

import com.api.project.domain.jobhistory.domain.JobHistory;
import com.api.project.exception.NotFoundEmployeeException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeJobHistoryFinderTest {
    private final EmployeeJobHistoryFinder finder;

    @Autowired
    public EmployeeJobHistoryFinderTest(EmployeeJobHistoryFinder finder) {
        this.finder = finder;
    }

    @Test
    void 직원의_이력_찾기_정상_작동() {
        List<JobHistory> jobHistories = finder.findByEmployeeId(101L);

        JobHistory one = jobHistories.get(0);
        JobHistory two = jobHistories.get(1);

        assertEquals(2, jobHistories.size());
        assertEquals("AC_ACCOUNT", one.getJob().getJobId());
        assertEquals("AC_MGR", two.getJob().getJobId());
    }

    @Test
    void 직원의_아이디가_다르면_익셉션_발생() {
        assertThrows(NotFoundEmployeeException.class, () -> finder.findByEmployeeId(9999999L));
    }
}