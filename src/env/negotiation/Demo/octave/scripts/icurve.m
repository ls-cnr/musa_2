function d = icurve( alpha, beta, gamma, u )

    %% ICURVE Coubb-Douglas indifference plane curve
    % Approximated to a set of 100 points

    d.y = linspace(0.1, 1);
    d.x = nthroot((ones(1, length(d.y)) .* (u / gamma)) ./ (d.y.^beta), alpha);

end
