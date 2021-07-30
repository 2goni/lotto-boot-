function shareTwitter() {
    var sendText = "황금비 로또추천 사이트"; // 전달할 텍스트
    var sendUrl = "randomluckygames.site/"; // 전달할 URL
    window.open("https://twitter.com/intent/tweet?text=" + sendText + "&url=" + sendUrl);
}

function shareFacebook() {
    var sendUrl = "randomluckygames.site/"; // 전달할 URL
    window.open("http://www.facebook.com/sharer/sharer.php?u=" + sendUrl);
}
function shareKakao() {

    // 사용할 앱의 JavaScript 키 설정
    Kakao.init('a806aca4d566e824b55bc50988a6df01');

    // 카카오링크 버튼 생성
    Kakao.Link.createDefaultButton({
        container: '#btnKakao', // 카카오공유버튼ID
        objectType: 'feed',
        content: {
            title: "황금비 로또추천 사이트", // 보여질 제목
            description: "황금비 로또추천 사이트 입니다", // 보여질 설명
            imageUrl: "randomluckygames.site/", // 콘텐츠 URL
            link: {
                mobileWebUrl: "randomluckygames.site/",
                webUrl: "randomluckygames.site/"
            }
        }
    });
}