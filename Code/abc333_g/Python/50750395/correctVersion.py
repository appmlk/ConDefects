from math import gcd

def cfraction(a, b):
	ans = [b//a]
	while a != 1:
		a,b=b%a,a
		ans.append(b//a)
	return ans

def fromfrac(frac):
	num = frac[-1]
	den = 1
	for i in range(len(frac)-2, -1,-1):
		num,den=den,num
		num += den * frac[i]
	return den,num

def closer(a,b, n1,d1, n2,d2):
	na = abs(a*d1 - n1*b)
	da = b*d1
	nb = abs(a*d2 - n2*b)
	db = b*d2
	if na*db < nb * da:
		return n1,d1
	return n2,d2


r = input()
n = int(input())
p10 = len(r)-2
den = 1
while p10 != 0:
	den *= 10
	p10 -= 1

num=0
for i in range(2,len(r)):
	num=(ord(r[i])-ord('0')) + num*10

d=gcd(num,den)
num //= d
den //= d
frac = cfraction(num,den)
orig_num = num
orig_den = den

best_num=-1
best_den=-1
for i in range(len(frac)):
	cur = frac[0:i+1]

	l = cur[-1]//2
	r = cur[-1]
	while r >= l:
		cur[-1] = (l+r)//2
		num,den = fromfrac(cur)
		d = gcd(num,den)
		num //= d
		den //= d
		if den <= n:
			best_num,best_den=closer(orig_num,orig_den, best_num,best_den,num,den)
			l = cur[-1]+1
		else: r = cur[-1]-1

if best_num == -1:
	if orig_num * 2 == orig_den:
		best_num=0
		best_den=1
	elif n * orig_num > orig_den:
		best_num=1
		best_den=n
	else:
		best_num=0
		best_den=1

best_num,best_den=closer(orig_num,orig_den, best_num,best_den,0,1)
best_num,best_den=closer(orig_num,orig_den, best_num,best_den,1,n)

if best_num * orig_den > orig_num * best_den:
	snd_num = 2*orig_num*best_den-orig_den*best_num
	snd_den=orig_den*best_den
	d = gcd(snd_num,snd_den)
	snd_num //= d
	snd_den //= d
	if snd_den <= n:
		best_num,best_den=snd_num,snd_den


print(f"{best_num} {best_den}")
