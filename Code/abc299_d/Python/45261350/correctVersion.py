N = int(input())
l=0; r=N
while r-l>1:
	m = (l+r)//2
	print('?', m)
	a = input()
	if a=='0': l = m
	else     : r = m
print('!', l)
