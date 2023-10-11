t=int(input())
bits=[1<<i for i in range(60)]
def r(x):
  if x<7:
    return -1
  cnt=0
  bs = []
  for i in range(60)[::-1]:
    if t&bits[i]:
      if cnt==2:
        return bits[bs[0]]+bits[bs[1]]+bits[i]
      bs.append(i)
      cnt+=1
  if cnt==1:
    return bits[bs[0]-1]+bits[bs[0]-2]+bits[bs[0]-3]
  else:
    if bs[1]<2:
      return bits[bs[0]-1]+bits[bs[0]-2]+bits[bs[0]-3]
    return bits[bs[0]]+bits[bs[1]-1]+bits[bs[1]-2]
print(*[r(int(input())) for i in range(t)],sep="\n")