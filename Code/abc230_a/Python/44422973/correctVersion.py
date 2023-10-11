N = int(input())
if N < 10:
  print("AGC00" + str(N))
elif 10 <= N < 42:
  print("AGC0" + str(N))
elif N >= 42 :
  A = N + 1
  print("AGC0" + str(A))