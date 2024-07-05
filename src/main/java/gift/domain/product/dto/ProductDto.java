package gift.domain.product.dto;

import gift.domain.product.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

public record ProductDto(
    Long id,

    @NotBlank(message = "상품 이름은 필수 입력 필드이며 공백으로만 구성될 수 없습니다.")
    @Size(max = 15, message = "상품 이름은 15자를 초과할 수 없습니다.")
    @Pattern(regexp = "[a-zA-z0-9ㄱ-ㅎㅏ-ㅣ가-힣()\\[\\]+\\-&/_\\s]+", message = "(,),[,],+,-,&,/,_ 외의 특수 문자는 사용이 불가능합니다.")
    @Pattern(regexp = "(?!.*카카오).*", message = "\"카카오\"가 포함된 문구는 담당 MD와 협의 후 사용 가능합니다.")
    String name,

    @Range(min = 1, max = Integer.MAX_VALUE, message = "상품 가격은 1 이상 " + Integer.MAX_VALUE + " 이하여야 합니다.")
    int price,

    @NotBlank(message = "상품 이미지 주소는 필수 입력 필드입니다.")
    @URL(message = "잘못된 URL 형식입니다.")
    String imageUrl)
{
    public Product toProduct() {
        return new Product(id, name, price, imageUrl);
    }
}
