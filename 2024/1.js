const fs = require('fs');
const path = require('path');

let str = fs.readFileSync(path.resolve(__dirname, '1-data.txt'), 'utf8')

const arr1 = [], arr2 = [];

str.trim().split('\n').forEach(item => {
  const [item1, item2] = item.split("   ");
  arr1.push(item1);
  arr2.push(item2);
});

arr1.sort();
arr2.sort();

let sum = 0;
for (let i = 0; i < arr1.length; i++) {
  sum += Math.abs(arr1[i] - arr2[i]);
}

console.log(sum)

sum = 0;
arr1.forEach(item => {
  let times = 0;
  arr2.forEach(num => {
    if (num == item) times++;
  })
  sum += times * item;
})

console.log(sum);

