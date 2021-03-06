module.exports = {
	env: {
		browser: true,
		es2021: true,
	},
	extends: ["naver", "plugin:react-hooks/recommended"],
	parser: "@typescript-eslint/parser",
	parserOptions: {
		ecmaFeatures: {
			jsx: true,
		},
		ecmaVersion: "latest",
	},
	plugins: ["react", "@typescript-eslint"],
	rules: {
		"no-unused-vars": "warn",
		"no-shadow": "off",
		"@typescript-eslint/no-shadow": ["error"],
	},
};
