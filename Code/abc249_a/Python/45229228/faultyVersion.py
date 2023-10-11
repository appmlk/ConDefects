a,b,c,d,e,f,x=map(int,input().split())
ta=(x//(a+c))*a*b+min(x%(a+c),a)*b
ao=(x//(d+f))*d*e+min(x%(d+f),d)*e
if ta>ao:
  print("takahashi")
elif ao>ta:
  print("Aoki")
else:
  print("Draw")