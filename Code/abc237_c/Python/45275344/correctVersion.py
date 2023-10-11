s = input()
n = len(s)
i, j = 0, n-1
while j >= 0:
	if s[j] == 'a':
		j -= 1
	else:
		break
if j < 0:
	print('Yes')
	exit(0)
while i < j:
	if s[i] == 'a':
		i += 1
	else:
		break
if i == j:
  print('Yes')
  exit(0)
st, ed = i, n-j-1
t = s[i : j+1]
if t != t[::-1]:
	print('No')
	exit(0)
if st <= ed:
	print('Yes')
else:
	print('No')