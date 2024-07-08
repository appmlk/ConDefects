def push_down(sx, sy, tx, ty):
  
  if ty == 0:
    return 0, sx, sy, tx, ty
    
  times = abs(tx - sx) + abs(ty + 1 - sy) + ty
  
  if sx == tx and sy < ty:
    times += 2
  
  return times, tx, 1, tx, 0


sx, sy, tx, ty, gx, gy = map(int, input().split())

sx -= gx
sy -= gy
tx -= gx
ty -= gy

if tx < 0:
  tx *= -1
  sx *= -1

if ty < 0:
  ty *= -1
  sy *= -1

ans = []

for _ in range(2):
  times_1, sx_2, sy_2, tx_2, ty_2 = push_down(sx, sy, tx, ty)
  times_2, *_ = push_down(sy_2, sx_2, ty_2, tx_2)
  ans.append(times_1 + times_2)
 
  sx, sy, tx, ty = sy, sx, ty, tx

print(min(ans))