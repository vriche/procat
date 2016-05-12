store.set('username', 'marcus')
store.get('username')
store.remove('username')
 
store.clear()
 
store.set('user', { name: 'marcus', likes: 'javascript' })
 
var user = store.get('user')
alert(user.name + ' likes ' + user.likes)
 
// Get all stored values
store.getAll().user.name == 'marcus'
 
// Loop over all stored values
store.forEach(function(key, val) {
    console.log(key, '==', val)
})