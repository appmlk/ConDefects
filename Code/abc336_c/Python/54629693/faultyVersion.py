# 5進数で考える

N = int(input()) - 1
rems = []
while N > 0:
  quotient = N // 5
  remainder = N % 5
  rems.append(remainder)
  N = quotient 
converted = rems[::-1]
converted = ''.join([str(c * 2) for c in converted])
print(converted)
