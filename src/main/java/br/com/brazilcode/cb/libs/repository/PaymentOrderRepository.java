package br.com.brazilcode.cb.libs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.brazilcode.cb.libs.model.PaymentOrder;

/**
 * Classe responsável por realizar as operações de persistência de entidade.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 6 de mar de 2020 08:59:37
 * @version 1.0
 */
public interface PaymentOrderRepository extends PagingAndSortingRepository<PaymentOrder, Long> {

	/**
	 * Método responsável por buscar um PaymentOrder pelo ID do PurchaseRequest informado.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param idArea
	 * @return
	 */
	@Query("SELECT po FROM payment_order po WHERE po.purchaseRequest = :idPurchaseRequest")
	PaymentOrder findByPurchaseRequestId(@Param("idPurchaseRequest") final Long idPurchaseRequest);

	/**
	 * Método responsável por buscar todos os PaymentOrder do ID da Area informado.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param idArea
	 * @return
	 */
	@Query("SELECT po FROM payment_order po WHERE po.area = :idArea")
	List<PaymentOrder> findByAreaId(@Param("idArea") final Long idArea);

	/**
	 * Método responsável por buscar todos os PaymentOrder do ID do Supplier informado.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param idSupplier
	 * @return
	 */
	@Query("SELECT po FROM payment_order po WHERE po.supplier = :idSupplier")
	List<PaymentOrder> findBySupplierId(@Param("idSupplier") final Long idSupplier);

}
