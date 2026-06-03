const axios = require("axios");
const config = require("./constants");

async function getToken() {

    const payload = {
        email: config.EMAIL,
        name: config.NAME,
        rollNo: config.ROLL_NO,
        accessCode: config.ACCESS_CODE,
        clientID: config.CLIENT_ID,
        clientSecret: config.CLIENT_SECRET
    };

    try {

        const response = await axios.post(
            `${config.BASE_URL}/auth`,
            payload
        );

        return response.data.access_token;

    } catch (error) {

        console.log(
            error.response?.data || error.message
        );
    }
}

module.exports = getToken;