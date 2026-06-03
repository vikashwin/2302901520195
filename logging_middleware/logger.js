const axios = require("axios");
const getToken = require("./auth");
const config = require("./constants");

async function Log(stack, level, pkg, message) {

    try {

        const token = await getToken();

        console.log("TOKEN =>", token);

        const body = {
            stack,
            level,
            package: pkg,
            message
        };

        const response = await axios.post(
            `${config.BASE_URL}/logs`,
            body,
            {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            }
        );

        console.log(response.data);

    } catch(error) {

        console.log(
            error.response?.data || error.message
        );
    }
}

module.exports = Log;