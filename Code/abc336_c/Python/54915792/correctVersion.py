n = int(input()) - 1

goodint = []
if n == 0:
    print(0)
while n != 0:
    goodint.append(n % 5)
    n //= 5

goodint = goodint[::-1]
new_list = []
for i in goodint:
    print(2*i, end = "")