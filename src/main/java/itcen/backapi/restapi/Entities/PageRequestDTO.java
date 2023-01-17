package itcen.backapi.restapi.Entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString @Getter @Setter
public class PageRequestDTO {

    private int page;  // 요청한 페이지 번호
    private int sizePerPage;  // 한페이지 당 데이터 개수

    // 기본생성자 => 초기 세팅 => int 기본 값 0 으로 잡혀있으니까 기본 값 1 로, sizeperPage 설정
    public PageRequestDTO() {
        this.page = 1;
        this.sizePerPage = 10;
    }

    // Setter 이용 트롤 처리
    public void setPage(int page) {
        // 예외처리 => 공격자들이 페이지 번호를 음수를 줘버리거나, 이상한 값을 줄 경우, page 1 로 설정. 그리고 날려보냄
        if (page < 0 || page > Integer.MAX_VALUE) {
            this.page = 1;
            return;
        }
        this.page = page;   // 기본 세터
    }

    public void setSizePerPage(int sizePerPage) {
        if (sizePerPage < 10 || sizePerPage > 100) {
            this.sizePerPage = 10; // 이거 공격자가 선넘게 이상하게 고쳐두면  10 으로 설정.
            return;
        }
        this.sizePerPage = sizePerPage;
    }





}
