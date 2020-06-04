const BASE_URL = 'http://localhost:8080';

const CARDS_URL = '/cards';
const GROUPS_URL = '/groups';
const TEST = "/test";
const USER_IMG_URL = "/user-img-url";
const USER_NAME_URL = "/user-name-url";
const ALL = '/all';
const SAVE_CORRECT_TEST_RESULT = '/save-correct-test-result';
const SAVE_WRONG_TEST_RESULT = '/save-wrong-test-result';
const EDIT_GROUP = "/edit-group";
const EDIT_CARDS = "/edit-cards";
const IS_GROUP_HAS_CARDS = "/has-cards";

const instance = axios.create({
    baseURL: BASE_URL,
    timeout: 1000
});