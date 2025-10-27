<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="simple-paginator" data-search-form-class=".board-search-form">
  
        <button class="btn-prev" data-page-no="${param.pageNo - 1}"><img src="/img/chevron_left.png"></button>



        <button class="btn-next" data-page-no="${param.pageNo + 1}"><img src="/img/chevron_right.png"></button>

</div>

<script>
document.addEventListener("DOMContentLoaded", () => {
  document.querySelectorAll(".simple-paginator button").forEach(btn => {
    btn.addEventListener("click", e => {
      const pageNo = e.target.getAttribute("data-page-no");
      const form = document.querySelector(".board-search-form"); // 폼 클래스 확인
      if (form && pageNo !== null) {
        let input = form.querySelector("input[name='pageNo']");
        if (!input) {
          input = document.createElement("input");
          input.type = "hidden";
          input.name = "pageNo";
          form.appendChild(input);
        }
        input.value = pageNo;
        form.submit();
      }
    });
  });
});
</script>

<style>
.simple-paginator {
  display: flex;
  justify-content: center;
  gap: 10px;
  margin-top: 20px;
}

.simple-paginator button {
  background-color: #5275C1;
  color: white;
  border: none;
  padding: 8px 14px;
  border-radius: 6px;
  cursor: pointer;
}

.simple-paginator button:hover {
  background-color: #3f5b9b;
}
</style>
