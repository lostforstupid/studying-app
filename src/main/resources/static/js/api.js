const BASE_URL = 'http://localhost:8080';

const CARDS_URL = '/cards';

const instance = axios.create({
    baseURL: BASE_URL,
    timeout: 1000
});