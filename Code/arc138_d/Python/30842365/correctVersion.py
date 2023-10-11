import sys
from collections import deque,defaultdict

def input():
    return sys.stdin.readline()[:-1]

def popcount(n):
	#O(logn)
	answer=0
	while n>0:
		answer+=n&0b1
		n>>=1
	return answer

def popcount_fast(n):
	assert n.bit_length()<=64
	x = x - ((x >> 1) & 0x5555555555555555)
	x = (x & 0x3333333333333333) + ((x >> 2) & 0x3333333333333333)
	x = (x + (x >> 4)) & 0x0f0f0f0f0f0f0f0f
	x = x + (x >> 8)
	x = x + (x >> 16)
	x = x + (x >> 32)
	return x & 0x0000007f

def graycode(n):
	return n^(n>>1)

def get_base(bases,n):
	res=[]
	tmp=[]
	for i in range(len(bases)):
		if len(res)>=n:
			return res
		v=bases[i]
		#print("v",v)
		for e in tmp:
			v=min(v,v^e)
			#print(v)
		if v>0:
			tmp.append(v)
			res.append(bases[i])
	return res

def main():
	n,k=map(int,input().split())
	answer="Yes" if n==1 or k&0b1==1 and k<n else "No"
	print(answer)
	if answer=="Yes":
		array=[]
		bases=list(filter(lambda x: popcount(x)==k,range(1<<n)))
		bases=get_base(bases,n)
		#print(bases)
		graycodes=list(map(graycode,range(1<<n)))

		for gc in graycodes:
			answer=0
			for i in range(n):
				if gc&0b1==1:
					answer^=bases[i]
				gc>>=1
			array.append(answer)
		print(*array)



	return 0

if __name__=="__main__":
	main()