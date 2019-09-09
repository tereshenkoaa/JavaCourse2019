package pro.sisit.lvm444.utest.TestDrivenApplication.mockito;

import pro.sisit.lvm444.utest.TestDrivenApplication.mockito.domain.ClientUser;
import pro.sisit.lvm444.utest.TestDrivenApplication.mockito.domain.Product;

public interface ProductDiscountService {

    public double calculateDiscount(Product product1, ClientUser client);
}
