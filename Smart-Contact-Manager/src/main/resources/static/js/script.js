document.addEventListener("DOMContentLoaded", (event) => {

	let currentTheme = getTheme();
	changeTheme();

	function changeTheme() {

		document.querySelector('html').classList.add(currentTheme);

		//setting listner
		const changeThemeBtn = document.getElementById("theme-change-buttom");
		changeThemeBtn.addEventListener("click", (event) => {

			const oldTheme = currentTheme;

			currentTheme = currentTheme == "light" ? "dark" : "light";

			setTheme(currentTheme);

			document.querySelector('html').classList.remove(oldTheme);
			document.querySelector('html').classList.add(currentTheme);
			const test = changeThemeBtn.querySelector("span").textContent = currentTheme == "light" ? "Dark" : "Light";

		})
	}

	function setTheme(theme) {
		localStorage.setItem("theme", theme);
	}

	function getTheme() {
		let theme = localStorage.getItem("theme");
		return theme ? theme : "light";
	}

});