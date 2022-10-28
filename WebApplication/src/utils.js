export  const  initialState = { isDarkMode:  true };

export  const  themeReducer = (state, action) => {
	switch (action) {
		case  "SET_LIGHT_MODE":
			return { isDarkMode:  false };
		case  "SET_DARK_MODE":
			return { isDarkMode:  true };
		default:
			return  state;
	}
};