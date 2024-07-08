import sys
sys.set_int_max_str_digits(200000)

u = int("5"*2+"0"*59)

num = ""
for i in range(1,59):
    num += (str(u//(2**i)))*(i//9+1 if i>30 else 1)
num = int(num)

# print(len(str(num)))

def dsum(num): return sum(map(int,str(num)))

print(num)

s = dsum(num)
for _ in range(50):
    num *= 2
    t = dsum(num)
    if s < t: print("rip",_,s,t,num)
    s = t