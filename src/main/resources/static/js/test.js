new Vue({
    el: '#app',
    data: {
        cards: [],
        testResults: [],
        currentCard: null,
        currentCardIndex: 0,
        userImgUrl: '',
        front: true,
        back: false,
        checkButton: true,
        nextButton: false,
        completeButton: false
    },
    created: function () {
        this.cards = cards;
        this.currentCard = this.cards[0];
        axios.get(USER_IMG_URL).then(response => {
            this.userImgUrl = response.data;
        });
    },
    methods: {
        check() {
            this.front = false;
            this.back = true;
            this.checkButton = false;
            this.nextButton = true;
        },
        correct() {
            this.next(true);
        },
        wrong() {
            this.next(false);
        },
        next(isAnsweredCorrectly) {
            this.testResults.push({cardId: this.currentCard.id,
                isAnsweredCorrectly: isAnsweredCorrectly});
            if (this.currentCardIndex < this.cards.length - 1) {
                this.currentCardIndex++;
                this.currentCard = this.cards[this.currentCardIndex];
                this.front = true;
                this.back = false;
                this.checkButton = true;
                this.nextButton = false;
            } else {
                this.saveResults();
            }
        },
        saveResults() {
            this.testResults.forEach(testResult => {
                if (testResult.isAnsweredCorrectly) {
                    instance.post(CARDS_URL + SAVE_CORRECT_TEST_RESULT + "/" + testResult.cardId, {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    });
                } else {
                    instance.post(CARDS_URL + SAVE_WRONG_TEST_RESULT + "/" + testResult.cardId, {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    });
                }
            });
        }
    }
});