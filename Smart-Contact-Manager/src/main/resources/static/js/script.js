document.addEventListener("DOMContentLoaded", (event) => {

	let currentTheme = getTheme();
	changeTheme();

	function changeTheme() {

		document.querySelector('html').classList.add(currentTheme);

		//setting listner
		const changeThemeBtn = document.getElementById("theme-change-buttom");

		changeThemeBtn.querySelector("span").textContent = currentTheme == "light" ? "Dark" : "Light";

		changeThemeBtn.addEventListener("click", (event) => {

			const oldTheme = currentTheme;

			currentTheme = currentTheme == "light" ? "dark" : "light";

			setTheme(currentTheme);

			document.querySelector('html').classList.remove(oldTheme);
			document.querySelector('html').classList.add(currentTheme);

			changeThemeBtn.querySelector("span").textContent = currentTheme == "light" ? "Dark" : "Light";
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

/*--------------------------------------------------------------------------------------------------------------------------------*/
/*Toaster functionality*/

function showToast(message, type) {
	if (!message || !type) return;

	toastr.options = {
		positionClass: 'toast-bottom-left',
		timeOut: 5000,
		closeButton: true,
		progressBar: true,
		newestOnTop: true,
		preventDuplicates: true
	};

	switch (type) {
		case 'success':
			toastr.success(message);
			break;
		case 'error':
			toastr.error(message);
			break;
		case 'info':
			toastr.info(message);
			break;
		case 'warning':
			toastr.warning(message);
			break;
		default:
			console.warn('Unknown toast type:', type);
	}

}

/*--------------------------------------------------------------------------------------------------------------------------------*/
// Image Preview in add contact form
function imagePreview() {

	console.log("accessable")

	document.getElementById("file_input").addEventListener("change", function(event) {
		let file = event.target.files[0];
		let reader = new FileReader();

		reader.onload = function() {
			document.getElementById("preview_image").src = reader.result;
		};

		reader.readAsDataURL(file)

	})
}


