const defaultTheme = require("tailwindcss/defaultTheme");
module.exports = {
  future: {
    removeDeprecatedGapUtilities: true,
  },
  theme: {
    extend: {
      backgroundImage: (theme) => ({
        "flase-left": "url('/images/flaše-left.png')",
        "flase-right": "url('/images/flaše-right.png')",
      }),
    },
  },
};
