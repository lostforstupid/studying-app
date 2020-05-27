new Vue({
    el: '#app',
    data: {
        cards: [],
        currentCard: null,
        currentCardIndex: 0,
        front: true,
        back: false,
        checkButton: true,
        nextButton: false,
        completeButton: false
    },
    created: function () {
        this.cards = cards;
        this.currentCard = this.cards[0];
    },
    methods: {
        check() {
            this.front = false;
            this.back = true;
            this.checkButton = false;
            const nextCurrentCardIndex = this.currentCardIndex + 1;
            if (nextCurrentCardIndex < this.cards.length) {
                this.nextButton = true;
            } else {
                this.completeButton = true;
            }
        },
        next() {
            this.currentCardIndex++;
            if (this.currentCardIndex < this.cards.length) {
                this.currentCard = this.cards[this.currentCardIndex];
                this.front = true;
                this.back = false;
                this.checkButton = true;
                this.nextButton = false;
            }
        }
    }
});