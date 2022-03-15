function remove(id) {
    fetch('/products/update?id=' + id, { method: 'DELETE' }).then(result =>
        console.log(result));
            refresh();
    }

function refresh() {
    document.location.assign("/products")
    }