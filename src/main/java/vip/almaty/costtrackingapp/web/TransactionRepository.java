package vip.almaty.costtrackingapp.web;

import org.springframework.data.jpa.repository.JpaRepository;
import vip.almaty.costtrackingapp.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>
{

}


