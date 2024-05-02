$(document).ready(function() {
    // 가격 표시
    var price = $('input[name="price"]').val();
    // 콤마를 제거한 후 숫자로 변환
    var numPrice = parseInt(price.replace(/,/g, ''));
    $('input[name="price"]').val(priceToString(numPrice));

    // 수량 표시
    var qty = $('input[name="qty"]').val();
    // 콤마를 제거한 후 숫자로 변환
    var numQty = parseInt(qty.replace(/,/g, ''));
    $('input[name="qty"]').val(qtyToString(numQty));
});

function priceToString(price) {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}

function qtyToString(qty) {
    return qty.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}
