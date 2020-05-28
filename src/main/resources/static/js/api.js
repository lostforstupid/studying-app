const BASE_URL = 'http://localhost:8080';

const CARDS_URL = '/cards';
const GROUPS_URL = '/groups';
const TEST = "/test";
const USER_IMG_URL = "/user-img-url";
const ALL = '/all';

const instance = axios.create({
    baseURL: BASE_URL,
    timeout: 1000
});