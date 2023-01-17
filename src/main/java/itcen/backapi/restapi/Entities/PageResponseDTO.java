package itcen.backapi.restapi.Entities;

import lombok.*;
import org.springframework.data.domain.Page;

@ToString @Setter @Getter
@Builder @NoArgsConstructor @AllArgsConstructor
public class PageResponseDTO<T> {
    // Paging 처리 할 수 있는 정보들 ( startPage, endPage, currentPage, prev, next, totalCount ) 넘겨줘야 함
    // current 페이지 기준으로
    // start end 는 페이징 번호 몇번 부터 몇번까지 보여줄 지
    // prev next 는 이전 다음 활성화

    private int startPage;
    private int endPage;
    private int currentPage;
    private boolean prev;
    private boolean next;
    private int totalCount;

    // prev / next 당 보여 줄 페이지 수
    private static final int PAGE_COUNT = 3;

    // 생성자 설정
    public PageResponseDTO(Page<T> pageData) {
        // index 니까 +1 해줘야 클라에서 보이는 진짜 currentPage 숫자
        this.currentPage = pageData.getPageable().getPageNumber() + 1; //Pageable 쓰면 PageNumber 가져올 수 있음

        // current가 34 번째 페이지 라면, 페이지 개수 10 개로 잡았을 때, 3.4 ceil => 4 => 40 페이지로 endPage를 만들겠다.
        // 나눌 때, int / int 로 나누면 int 로만 나오니까, double 변환 해줘야 3.4 로 나오겠지
        this.endPage = (int)Math.ceil((double)currentPage / PAGE_COUNT) * PAGE_COUNT;
        // 근데 이거 찐 마지막 페이징에서는 적용 안됨 => 보정설정

        this.startPage = endPage - PAGE_COUNT + 1;
        this.totalCount = (int)pageData.getTotalElements(); //long 타입이라 int 로 다운그레이드

//        int realEnd = (int) Math.ceil((double) totalCount / pageData.getSize());
        // JPA 로는
        int realEnd = pageData.getTotalPages(); // 실제 end 페이지 구해줌

        /*if (startPage > 1) {
            this.prev = true;
        } else {
            this.prev = false;
        } */
        this.prev = startPage > 1;  // startPage 가 1 보다 크면 true 다. 1이면 false 처리

        if (realEnd < endPage) {
            this.endPage = realEnd;
        }
        // 마지막 구간에서만 보정해주는 설정 - endPage 를 realEnd 페이지로 설정
        this.next = endPage < realEnd;
    }
}
