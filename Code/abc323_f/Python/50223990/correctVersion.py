import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**7)

def II(): return int(input())
def MI(): return map(int,input().split())
def LM(): return list(MI())
def LL(n): return [LM() for _ in range(n)]
def LS(n,remove_br=False): return [list(input())[:-1] if remove_br else list(input()) for _ in range(n)]

def MI_1(): return map(lambda x:int(x)-1,input().split())
def LM_1(): return list(MI_1())
def LL_1(n): return [LM_1() for _ in range(n)]

def bit_count(num):
	length = num.bit_length()
	res = 0
	for i in range(length):
		if num >> i & 1:
			res += 1
	return res

# clockwise from top.
DIRECTION_4 = [[-1,0],[0,1],[1,0],[0,-1]] 
DIRECTION_8 = [[-1,0],[-1,1],[0,1],[1,1],[1,0],[1,-1],[0,-1],[-1,-1]] 
MOD = 998244353
INF = float("inf")

def DB(*x):
	global DEBUG_MODE
	if DEBUG_MODE: print(*x)
############
DEBUG_MODE=1
############




def rot(x,y):	return (-y,x)

ax,ay,bx,by,cx,cy = MI()
ppl = (ax-bx,ay-by)
goal = (cx-bx,cy-by)
while not (goal[0] > 0 and goal[1] >= 0):
	ppl = rot(*ppl)
	goal = rot(*goal)

x,y = ppl
w,h = goal



if h == 0:
	if x>0 and y==0:
		print(x+3+w)
	else:
		print(abs(x+1)+abs(y)+w)
elif w == 0:
	if y>0 and x==0:
		print(y+3+w)
	else:
		print(abs(x)+abs(y+1)+h)

elif y<0:
	print(abs(x)+abs(y+1)+h+w+2)
elif x<0:
	print(abs(x+1)+abs(y)+h+w+2)
else:
	print(abs(x)+abs(y)+1+h+w+2)