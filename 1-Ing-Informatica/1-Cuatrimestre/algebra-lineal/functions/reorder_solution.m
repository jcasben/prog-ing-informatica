function reordered_solution = reorder_solution(solution, permutation)
  n = length(solution);
  reordered_solution = zeros(size(solution));

  for k = 1:n
    reordered_solution(permutation(k)) = solution(k);
  endfor
endfunction
