a, b, C = map(int, input().split())

def popcount(X, digit):
	ret = 0
	for i in range(digit):
		if (1 << i & X):
			ret += 1
	return(ret)

c = popcount(C, 60)

if a + b + c > 120 or (a + b + c) % 2 == 1:
	print(-1)
	exit()

if a > b + c or b > a + c or c > a + b:
	print(-1)
	exit()

n00 = 60 - (a + b + c) // 2
n01 = (-a + b + c) // 2
n10 = (a - b + c) // 2
n11 = (a + b - c) // 2

X = ['0'] * 60
Y = ['0'] * 60
for i in range(60):
	if (1 << i & C):
		if n10 > 0:
			X[59 - i] = '1'
			n10 -= 1
		else:
			Y[59 - i] = '1'
			n01 -= 1
	else:
		if n11 > 0:
			X[59 - i] = '1'
			Y[59 - i] = '1'
			n11 -= 1
		else:
			n00 -= 1

print(int(('').join(map(str, X)), 2), int(('').join(map(str, Y)), 2))